# mima-cli

This is a command-line interface over [MiMa](https://github.com/lightbend/mima). You can use it to generate a report of
binary incompatibility issues that are introduced between two versions of a Scala libarary.

It is neither endorsed nor supported by Lightbend. All it does is invoke MiMa.

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

## License

It's under the Apache 2.0 License, because that's what MiMA has. [Click here for MiMa's license.](https://github.com/lightbend/mima/blob/main/LICENSE)


