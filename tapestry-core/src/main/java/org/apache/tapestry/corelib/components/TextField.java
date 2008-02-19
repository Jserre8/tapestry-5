// Copyright 2006, 2007, 2008 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.tapestry.corelib.components;

import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.corelib.base.AbstractTextField;

/**
 * TextField component corresponds to &lt;input type="text"&gt; element. The value parameter will be
 * editted. TextField is generally used with string values, but other values are acceptible, as long
 * as they can be freely converted back and forth to strings.
 */
public final class TextField extends AbstractTextField
{
    @Override
    protected final void writeFieldTag(MarkupWriter writer, String value)
    {
        writer.element("input",

                       "type", "text",

                       "name", getControlName(),

                       "id", getClientId(),

                       "value", value);
    }

    final void afterRender(MarkupWriter writer)
    {
        writer.end(); // input
    }

}
