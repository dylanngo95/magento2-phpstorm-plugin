/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.data;

public class NewUnitTestData {

    private String directory;
    private String className;
    private String namespace;
    private String moduleName;

    /**
     * Constructor NewUnitTestData.
     * @param directory
     * @param className
     * @param namespace
     */
    public NewUnitTestData(String directory, String className, String namespace, String moduleName) {
        this.directory = directory;
        this.className = className;
        this.namespace = namespace;
        this.moduleName = moduleName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
