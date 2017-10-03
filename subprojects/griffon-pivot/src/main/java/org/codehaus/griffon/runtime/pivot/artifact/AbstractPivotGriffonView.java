/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2008-2018 the original author or authors.
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
package org.codehaus.griffon.runtime.pivot.artifact;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.Action;
import griffon.pivot.support.PivotAction;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Pivot-friendly implementation of the GriffonView interface.
 *
 * @author Andres Almiray
 * @since 2.0.0
 */
public abstract class AbstractPivotGriffonView extends AbstractGriffonView {
    public AbstractPivotGriffonView() {

    }

    @Nullable
    protected PivotAction toolkitActionFor(@Nonnull GriffonController controller, @Nonnull String actionName) {
        Action action = actionFor(controller, actionName);
        return action != null ? (PivotAction) action.getToolkitAction() : null;
    }
}
