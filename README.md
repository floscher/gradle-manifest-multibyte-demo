# Multibyte characters are split in two

## Demo that Gradle produces JAR files with cut-apart multibyte characters
Run
```
./gradlew jar
```

Then inspect the file `./build/lib/gradle-manifest-multibyte-demo.jar/META-INF/MANIFEST.MF`. Some multibyte characters are cut apart, because each line ends at exactly 72 bytes.

A copy of that `MANIFEST.MF` can be found at [`src/main/resources/MANIFEST.MF`](src/main/resources/MANIFEST.MF).

## Demo that Java (on which Gradle relies to generate manifest) also cuts the multibyte characters

Run
```
./gradlew run
```

You will see an analysis of both files [`src/main/resources/MANIFEST.MF`](src/main/resources/MANIFEST.MF) and [`src/main/resources/MANIFEST2.MF`](src/main/resources/MANIFEST2.MF) (identical files, the first with cut UTF-8 characters, the other one has all characters intact).
First you see how the attributes are represented when you deserialize the manifest using the `java.util.jar.Manifest` class from Java (identical for both files). Then you see how the same class serializes the manifest (also identical).

The [JAR File Specification](https://docs.oracle.com/javase/9/docs/specs/jar/jar.html#notes_on_manifest_and_signature_filesnotes-on-manifest-and-signature-files) states that the lines in a MANIFEST.MF file must be no longer than 72 bytes (explicitly not 72 characters). It does not explicitly say if it's ok to split multibyte characters.
