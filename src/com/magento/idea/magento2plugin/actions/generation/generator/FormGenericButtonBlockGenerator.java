/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.generator;

import com.intellij.openapi.project.Project;
import com.magento.idea.magento2plugin.actions.generation.data.FormGenericButtonBlockData;
import com.magento.idea.magento2plugin.actions.generation.generator.util.PhpClassGeneratorUtil;
import com.magento.idea.magento2plugin.actions.generation.generator.util.PhpClassTypesBuilder;
import com.magento.idea.magento2plugin.magento.files.AbstractPhpFile;
import com.magento.idea.magento2plugin.magento.files.FormGenericButtonBlockFile;
import com.magento.idea.magento2plugin.magento.packages.code.FrameworkLibraryType;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class FormGenericButtonBlockGenerator extends PhpFileGenerator {

    private final FormGenericButtonBlockData data;

    /**
     * Generic Button Block generator constructor.
     *
     * @param data FormGenericButtonBlockData
     * @param project Project
     */
    public FormGenericButtonBlockGenerator(
            final @NotNull FormGenericButtonBlockData data,
            final @NotNull Project project
    ) {
        this(data, project, true);
    }

    /**
     * Generic Button Block generator constructor.
     *
     * @param data FormGenericButtonBlockData
     * @param project Project
     * @param checkFileAlreadyExists boolean
     */
    public FormGenericButtonBlockGenerator(
            final @NotNull FormGenericButtonBlockData data,
            final @NotNull Project project,
            final boolean checkFileAlreadyExists
    ) {
        super(project, checkFileAlreadyExists);
        this.data = data;
    }

    @Override
    protected AbstractPhpFile initFile() {
        return new FormGenericButtonBlockFile(data.getModuleName(), data.getEntityName());
    }

    /**
     * Fill file property attributes.
     *
     * @param attributes Properties
     */
    @Override
    protected void fillAttributes(final @NotNull Properties attributes) {
        final PhpClassTypesBuilder phpClassTypesBuilder = new PhpClassTypesBuilder();
        final String entityIdGetter = "get" + Arrays.stream(data.getEntityId().split("_"))
                .map(s -> s.substring(0, 1).toUpperCase(Locale.getDefault()) + s.substring(1))
                .collect(Collectors.joining());

        phpClassTypesBuilder
                .appendProperty("NAMESPACE", file.getNamespace())
                .appendProperty("ENTITY_NAME", data.getEntityName())
                .appendProperty("CLASS_NAME", FormGenericButtonBlockFile.CLASS_NAME)
                .appendProperty("ENTITY_ID", data.getEntityId())
                .appendProperty("ENTITY_ID_GETTER", entityIdGetter)
                .append("CONTEXT", FormGenericButtonBlockFile.CONTEXT)
                .append("URL", FrameworkLibraryType.URL.getType())
                .mergeProperties(attributes);

        attributes.setProperty("USES",
                PhpClassGeneratorUtil.formatUses(phpClassTypesBuilder.getUses()));
    }
}
