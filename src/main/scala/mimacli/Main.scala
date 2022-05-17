package mimacli

import com.typesafe.tools.mima.lib.MiMaLib

import java.io.File
import scala.annotation.tailrec

case class Main(
  classpath: Seq[File] = Nil,
  oldBinOpt: Option[File] = None,
  newBinOpt: Option[File] = None
) {

  def run(): Unit = {
    val oldBin = oldBinOpt.getOrElse(throw new IllegalArgumentException("Old binary was not specified"))
    val newBin = newBinOpt.getOrElse(throw new IllegalArgumentException(""))
    // TODO: should have some machine-readable output here, as an option
    new MiMaLib(classpath).collectProblems(oldBin, newBin, Nil).foreach {
      problem => println(problem.description("new"))
    }
  }

}

object Main {

  def main(args: Array[String]): Unit =
    try parseArgs(args.toList, Main()).run() catch {
      case err: IllegalArgumentException =>
        println(err.getMessage())
        printUsage()
    }


  def printUsage(): Unit = println(
    s"""Usage:
      |
      |mima [-cp classpath] oldfile newfile
      |
      |  classpath: Java classpath, separated by '${File.pathSeparatorChar}'
      |  oldfile: Old (or, previous) files – a JAR or a directory containing classfiles
      |  newfile: New (or, current) files - a JAR or a directory containing classfiles
      |
      |""".stripMargin
  )

  @tailrec
  private def parseArgs(remaining: List[String], current: Main): Main = remaining match {
    case Nil => current
    case ("-cp" | "--classpath") :: cpStr :: rest => parseArgs(rest, current.copy(classpath = cpStr.split(File.pathSeparatorChar).map(new File(_))))
    case filename :: rest if current.oldBinOpt.isEmpty => parseArgs(rest, current.copy(oldBinOpt = Some(new File(filename))))
    case filename :: rest if current.newBinOpt.isEmpty => parseArgs(rest, current.copy(newBinOpt = Some(new File(filename))))
    case wut :: _ =>
      throw new IllegalArgumentException(s"Unknown argument $wut")
  }

}