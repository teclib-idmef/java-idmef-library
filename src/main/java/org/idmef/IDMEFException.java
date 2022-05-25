/*
 * Copyright (C) 2022 Teclib'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.idmef;

import java.io.IOException;

/**
 * IDMEF exception class.
 *
 * IDMEF exceptions are thrown when validating, serializing an object or deserializing bytes.
 *
 */
public class IDMEFException extends IOException {
    /**
     * Construct a IDMEFException
     *
     * @param message the message contained in the exception
     */
    public IDMEFException(String message) {
        super(message);
    }
}
