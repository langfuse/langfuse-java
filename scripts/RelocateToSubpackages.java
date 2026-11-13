///usr/bin/env jbang "$0" "$@" ; exit $?

//
// Relocates generated Java API files to match their declared package.
//
// The openapi-generator places all API files in a flat directory based on apiPackage,
// but our custom templates declare tag-based sub-packages (e.g., com.langfuse.api.health).
// This script reads each file's package declaration and moves it to the correct directory.
//
// Usage: RelocateToSubpackages <dir1> [dir2] ... [-- <glob-pattern>]
// Example: RelocateToSubpackages target/generated-sources/openapi-apis -- '*.java'
//

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class RelocateToSubpackages {

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("^package\\s+([^;]+);");

    public static void main(String[] args) throws IOException {
        var argList = List.of(args);
        var separatorIndex = argList.indexOf("--");
        var dirs = (separatorIndex >= 0) ? argList.subList(0, separatorIndex) : argList;
        var pattern = (separatorIndex >= 0 && separatorIndex + 1 < argList.size())
                ? argList.get(separatorIndex + 1)
                : "*.java";

        var matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);

        dirs.stream()
                .map(Path::of)
                .filter(Files::isDirectory)
                .flatMap(dir -> {
                    try {
                        return Files.walk(dir);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .filter(Files::isRegularFile)
                .filter(path -> matcher.matches(path.getFileName()))
                .forEach(RelocateToSubpackages::relocate);
    }

    private static void relocate(Path file) {
        try {
            Files.readAllLines(file).stream()
                    .flatMap(line -> PACKAGE_PATTERN.matcher(line).results())
                    .map(match -> match.group(1))
                    .findFirst()
                    .ifPresent(pkg -> moveToPackageDir(file, pkg));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void moveToPackageDir(Path file, String pkg) {
        var filePath = file.toAbsolutePath().toString().replace('\\', '/');
        var base = filePath.replaceFirst("(/src/main/java/).*", "$1");
        var targetDir = Path.of(base, pkg.replace(".", "/"));
        var currentDir = file.getParent().toAbsolutePath().normalize();

        if (!currentDir.equals(targetDir.toAbsolutePath().normalize())) {
            try {
                Files.createDirectories(targetDir);
                Files.move(file, targetDir.resolve(file.getFileName()));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
