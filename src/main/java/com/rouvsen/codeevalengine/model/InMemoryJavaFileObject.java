package com.rouvsen.codeevalengine.model;

import static javax.tools.JavaFileObject.Kind.SOURCE;

import java.net.URI;
import javax.tools.SimpleJavaFileObject;

public class InMemoryJavaFileObject extends SimpleJavaFileObject {
    final String sourceCode;

    public InMemoryJavaFileObject(String className, String sourceCode) {
        super(URI.create("string:///" + className.replace('.', '/') + ".java"), SOURCE);
        this.sourceCode = sourceCode;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return sourceCode;
    }
}
