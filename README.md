# Multibyte characters are split in two

Run
```
./gradlew jar
```

Then inspect the file `./build/lib/gradle-manifest-multibyte-demo.jar/META-INF/MANIFEST.MF`. Some multibyte characters are cut apart, because each line ends at exactly 72 bytes.

A copy of that `MANIFEST.MF` can be found at [`src/main/resources/MANIFEST.MF`](src/main/resources/MANIFEST.MF).

The [JAR File Specification](https://docs.oracle.com/javase/9/docs/specs/jar/jar.html#notes_on_manifest_and_signature_filesnotes-on-manifest-and-signature-files) states that the lines in a MANIFEST.MF file must be no longer than 72 bytes (explicitly not 72 characters). It does not explicitly say if it's ok to split multibyte characters.
