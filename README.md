# mima-cli

This is a command-line interface over [MiMa](https://github.com/lightbend/mima). You can use it to generate a report of
binary incompatibility issues that are introduced between two versions of a Scala libarary.

It is neither endorsed nor supported by Lightbend. All it does is invoke MiMa.

## Usage

`mima [-cp classpath] oldfile newfile`

- `classpath`: Java classpath to consider, usually separated by `:` (this isn't usually needed)
- `oldfile`: Old (or, previous) file – a JAR or a directory containing classfiles
- `newfile`: New (or, current) file - a JAR or a directory containing classfiles

## License

It's under the Apache 2.0 License, because that's what MiMA has. [Click here for MiMa's license.](https://github.com/lightbend/mima/blob/main/LICENSE)


