/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2008-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.builder.lanterna3.factory

import griffon.lanterna3.support.LanternaAction
import griffon.lanterna3.widgets.MutableButton

/**
 * @author Andres Almiray
 * @since 3.0.0
 */
class ButtonFactory extends ComponentFactory {
    ButtonFactory() {
        super(MutableButton, false)
    }

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        if (value instanceof LanternaAction) {
            return new MutableButton((LanternaAction) value)
        }

        def text = attributes.remove('text')
        if (text == null && value != null) text = String.valueOf(value)
        text = text != null ? text.toString() : ''

        def action = attributes.remove('action')
        if (action instanceof Runnable) {
            action = new LanternaAction(text, action)
        } else if (action != null && !(action instanceof LanternaAction)) {
            throw new IllegalArgumentException("In $name action: is not a Runnable nor a LanternaAction")
        }

        new MutableButton(text, action)
    }

    boolean isHandlesNodeChildren() {
        true
    }

    boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
        node.action = childContent
        false
    }
}
