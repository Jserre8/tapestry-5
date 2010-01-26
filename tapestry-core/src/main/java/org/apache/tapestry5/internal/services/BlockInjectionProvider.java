// Copyright 2007, 2008, 2010 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.tapestry5.internal.services;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Id;
import org.apache.tapestry5.ioc.ObjectLocator;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentValueProvider;
import org.apache.tapestry5.services.InjectionProvider;
import org.apache.tapestry5.services.TransformConstants;
import org.apache.tapestry5.services.TransformMethod;

/**
 * Identifies fields of type {@link Block} that have the {@link Inject} annotation and converts them
 * into read-only
 * fields containing the injected Block from the template. The annotation's value is the id of the
 * block to inject; if
 * omitted, the block id is deduced from the field id.
 * <p/>
 * Must be scheduled before {@link DefaultInjectionProvider} because it uses the same annotation, Inject, with a
 * different interpretation.
 */
public class BlockInjectionProvider implements InjectionProvider
{

    public boolean provideInjection(String fieldName, Class fieldType, ObjectLocator locator,
            ClassTransformation transformation, MutableComponentModel componentModel)
    {
        if (!fieldType.equals(Block.class))
            return false;

        Id annotation = transformation.getFieldAnnotation(fieldName, Id.class);

        final String blockId = getBlockId(fieldName, annotation);

        ComponentValueProvider<Block> provider = new ComponentValueProvider<Block>()
        {
            public Block get(ComponentResources resources)
            {
                return resources.getBlock(blockId);
            }
        };

        TransformMethod method = transformation
                .getMethod(TransformConstants.CONTAINING_PAGE_DID_ATTACH_SIGNATURE);

        transformation.getField(fieldName).assignIndirect(method, provider);

        return true; // claim the field
    }

    private String getBlockId(String fieldName, Id annotation)
    {
        if (annotation != null)
            return annotation.value();

        return InternalUtils.stripMemberName(fieldName);
    }
}
