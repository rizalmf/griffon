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
package editor;

import griffon.core.artifact.GriffonModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;
import org.kordamp.jipsy.ServiceProviderFor;

@ServiceProviderFor(GriffonModel.class)
public class ContainerModel extends AbstractGriffonModel {
    private static final String MVC_IDENTIFIER = "mvcIdentifier";
    private final DocumentModel documentModel = new DocumentModel();
    private StringProperty mvcIdentifier = new SimpleStringProperty(this, MVC_IDENTIFIER);

    public ContainerModel() {
        mvcIdentifier.addListener((value, oldValue, newValue) -> {
            Document document = null;
            if (newValue != null) {
                EditorModel model = getApplication().getMvcGroupManager().getModel(getMvcIdentifier(), EditorModel.class);
                document = model.getDocument();
            } else {
                document = new Document();
            }
            documentModel.setDocument(document);
        });
    }

    public StringProperty mvcIdentifierProperty() {
        return mvcIdentifier;
    }

    public String getMvcIdentifier() {
        return mvcIdentifier.get();
    }

    public void setMvcIdentifier(String mvcIdentifier) {
        this.mvcIdentifier.set(mvcIdentifier);
    }

    public DocumentModel getDocumentModel() {
        return documentModel;
    }
}