package sbtclosure

import com.google.javascript.jscomp.{Compiler => ClosureCompiler, CompilerOptions => ClosureCompilerOptions, JSError, SourceFile}

import scala.collection.JavaConversions._

import sbt._

class Compiler(options: ClosureCompilerOptions) {

  def compile(sources: List[File], externs: List[File], target: File, log: Logger): Unit = {
    val compiler = new ClosureCompiler

    val externList = externs.map(SourceFile.fromFile _)
    val sourceList = sources.map(SourceFile.fromFile _)

    log.debug("sources: " + sourceList.toString)
    log.debug("externs: " + externList.toString)
    log.debug("target: " + target.toString)
    
    val result = compiler.compile(
      externList,
      sourceList,
      options
    )

    val errors = result.errors.toList
    val warnings = result.warnings.toList

    if (!errors.isEmpty) {
      errors.foreach { (err: JSError) => log.error(err.toString) }
    }
    else {
      if (!warnings.isEmpty) {
        warnings.foreach { (err: JSError) => log.warn(err.toString) }
      }

      IO.createDirectory(file(target.getParent))
      IO.write(target, compiler.toSource)
    }
  }
}
