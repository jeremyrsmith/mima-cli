# mima-cli

This is a command-line interface over [MiMa](https://github.com/lightbend/mima). You can use it to generate a report of
binary incompatibility issues that are introduced between two versions of a Scala libarary.

It is neither endorsed nor supported by Lightbend. All it does is invoke MiMa.

## Installation

Each GitHub release has an assembly JAR attached to it, which should be directly executable. So just download it, name
it `mima-cli` and put it somewhere in your `PATH`.

Alternatively, it's also released to Maven Central, so you can probably use [Coursier's `bootstrap` command](https://get-coursier.io/docs/cli-bootstrap)
instead:

```bash
cs boostrap io.github.jeremyrsmith::mima-cli:0.1.0 -o mima
./mima
```

## Usage

```
mima [OPTIONS] oldfile newfile

  oldfile: Old (or, previous) files – a JAR or a directory containing classfiles
  newfile: New (or, current) files - a JAR or a directory containing classfiles
  
Options:
  -cp CLASSPATH:
     Specify Java classpath, separated by '${File.pathSeparatorChar}'
     
  -v, --verbose:
     Show a human-readable description of each problem
     
  -f, --forward-only:
    Show only forward-binary-compatibility problems
    
  -b, --backward-only:
    Show only backward-binary-compatibility problems
    
  -g, --include-generics:
    Include generic signature problems, which may not directly cause bincompat
    problems and are hidden by default. Has no effect if using --forward-only.
    
  -j, --bytecode-names:
    Show bytecode names of fields and methods, rather than human-readable names
```

## Output
If there are no binary incompatibilities to report, `mima-cli` prints nothing and exits with `0` status. Otherwise,
it exits with a status representing the number of binary compatibility problems, after printing a report of the problems
to stdout.

Here's a sample of default output, on two old versions of [circe](https://github.com/circe/circe) (I had to reach pretty
far back to find any examples of binary incompatibility!)

```
$ mima-cli circe-core_2.12-0.9.0.jar circe-core_2.12-0.10.0.jar
io.circe.ACursor.fieldSet: DirectMissingMethod
io.circe.ACursor.fields: DirectMissingMethod
io.circe.Decoder.decodeTraversable: DirectMissingMethod
io.circe.Decoder.and: DirectMissingMethod
io.circe.Decoder.split: DirectMissingMethod
io.circe.Decoder.decodeTraversable: DirectMissingMethod
io.circe.Decoder#DecoderWithFailure.and: DirectMissingMethod
io.circe.Decoder#DecoderWithFailure.split: DirectMissingMethod
io.circe.Encoder.importedEncoder: IncompatibleMethType
io.circe.Encoder.importedEncoder: IncompatibleMethType
io.circe.JsonNumber.truncateToByte: DirectMissingMethod
io.circe.JsonNumber.truncateToShort: DirectMissingMethod
io.circe.JsonNumber.truncateToInt: DirectMissingMethod
io.circe.JsonNumber.truncateToLong: DirectMissingMethod
io.circe.JsonObject.from: DirectMissingMethod
io.circe.JsonObject.fields: DirectMissingMethod
io.circe.JsonObject.fieldSet: DirectMissingMethod
io.circe.JsonObject.withJsons: DirectMissingMethod
io.circe.JsonObject.from: DirectMissingMethod
io.circe.RootEncoder.importedRootEncoder: IncompatibleMethType
io.circe.RootEncoder.importedRootEncoder: IncompatibleMethType
```

And a few lines of the corresponding `--verbose` output:

```
$ mima-cli -v circe-core_2.12-0.9.0.jar circe-core_2.12-0.10.0.jar
io.circe.ACursor.fieldSet: DirectMissingMethod: deprecated method fieldSet()scala.Option in class io.circe.ACursor does not have a correspondent in new version
io.circe.ACursor.fields: DirectMissingMethod: deprecated method fields()scala.Option in class io.circe.ACursor does not have a correspondent in new version
io.circe.Decoder.decodeTraversable: DirectMissingMethod: static method decodeTraversable(io.circe.Decoder,scala.collection.generic.CanBuildFrom)io.circe.Decoder in interface io.circe.Decoder does not have a correspondent in new version
```

And, a few lines of the corresponding `--bytecode-names` output (this is intended for use in other classfile tooling, like `javap`):

```
$ mima-cli -j circe-core_2.12-0.9.0.jar circe-core_2.12-0.10.0.jar
io/circe/ACursor.fieldSet()Lscala.Option;: DirectMissingMethod
io/circe/ACursor.fields()Lscala.Option;: DirectMissingMethod
io/circe/Decoder.decodeTraversable(Lio.circe.Decoder;Lscala.collection.generic.CanBuildFrom;)Lio.circe.Decoder;: DirectMissingMethod
```

## License

It's under the Apache 2.0 License, because that's what MiMA has. [Click here for MiMa's license.](https://github.com/lightbend/mima/blob/main/LICENSE)


