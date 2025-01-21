package com.rouvsen.codeevalengine.compiler;

import com.rouvsen.codeevalengine.model.CompilationResult;
import com.rouvsen.codeevalengine.model.InMemoryJavaFileObject;
import java.io.StringWriter;
import java.util.Collections;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class InMemoryJavaCompiler {

    public CompilationResult compile(String className, String sourceCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException(
                    "System Java Compiler not available. " +
                            "Make sure you're using a JDK (not just a JRE)."
            );
        }

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject fileObject = new InMemoryJavaFileObject(className, sourceCode);

        try (StandardJavaFileManager fileManager =
                     compiler.getStandardFileManager(diagnostics, null, null)) {

            JavaCompiler.CompilationTask task = compiler.getTask(
                    null,
                    fileManager,
                    diagnostics,
                    null,
                    null,
                    Collections.singletonList(fileObject)
            );

            boolean success = task.call();
            StringWriter errorOutput = new StringWriter();
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                errorOutput.append(diagnostic.toString()).append("\n");
            }

            return new CompilationResult(success, errorOutput.toString());
        } catch (Exception e) {
            return new CompilationResult(false, e.getMessage());
        }
    }
}
