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
    private String namespaceParentName;

    public NewUnitTestData(String directory, String className, String namespace, String moduleName, String namespaceParentName) {
        this.directory = directory;
        this.className = className;
        this.namespace = namespace;
        this.moduleName = moduleName;
        this.namespaceParentName = namespaceParentName;
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

    public String getNamespaceParentName() {
        return namespaceParentName;
    }

    public void setNamespaceParentName(String namespaceParentName) {
        this.namespaceParentName = namespaceParentName;
    }
}
