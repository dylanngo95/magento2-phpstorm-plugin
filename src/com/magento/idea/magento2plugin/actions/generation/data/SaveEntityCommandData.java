/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.data;

import org.jetbrains.annotations.NotNull;

public class SaveEntityCommandData {

    private final String moduleName;
    private final String entityName;
    private final String modelName;
    private final String resourceModelName;
    private final String dtoName;
    private final String dtoInterfaceName;
    private final boolean dtoWithInterface;

    /**
     * Save Command DTO Constructor.
     *
     * @param moduleName String
     * @param entityName String
     * @param modelName String
     * @param resourceModelName String
     * @param dtoName String
     * @param dtoInterfaceName String
     * @param isDtoWithInterface boolean
     */
    public SaveEntityCommandData(
            final @NotNull String moduleName,
            final @NotNull String entityName,
            final @NotNull String modelName,
            final @NotNull String resourceModelName,
            final @NotNull String dtoName,
            final @NotNull String dtoInterfaceName,
            final boolean isDtoWithInterface
    ) {
        this.moduleName = moduleName;
        this.entityName = entityName;
        this.modelName = modelName;
        this.resourceModelName = resourceModelName;
        this.dtoName = dtoName;
        this.dtoInterfaceName = dtoInterfaceName;
        this.dtoWithInterface = isDtoWithInterface;
    }

    /**
     * Get module name.
     *
     * @return String
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Get entity name.
     *
     * @return String
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Get model name.
     *
     * @return String
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Get resource model name.
     *
     * @return String
     */
    public String getResourceModelName() {
        return resourceModelName;
    }

    /**
     * Get DTO name.
     *
     * @return String
     */
    public String getDtoName() {
        return dtoName;
    }

    /**
     * Get DTO interface name.
     *
     * @return String
     */
    public String getDtoInterfaceName() {
        return dtoInterfaceName;
    }

    /**
     * Check if DTO has an interface.
     *
     * @return boolean
     */
    public boolean isDtoWithInterface() {
        return dtoWithInterface;
    }
}
