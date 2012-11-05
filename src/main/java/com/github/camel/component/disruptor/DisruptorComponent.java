/*
 * Copyright 2012 Riccardo Sirchia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.camel.component.disruptor;

import java.util.Map;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * TODO: documentation
 * Parameters:
 *  - bufferSize: size of the ringbuffer (will be rounded up to nearest power of 2), default 1024
 *  - concurrentConsumers: number of concurrent threads processing exchanges, default 1
 */
public class DisruptorComponent extends DefaultComponent {
    private int defaultBufferSize = 1024;

    private int defaultConcurrentConsumers = 1;

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        int bufferSize = getAndRemoveParameter(parameters, "bufferSize", int.class, defaultBufferSize);
        int concurrentConsumers = getAndRemoveParameter(parameters, "concurrentConsumers", int.class, defaultConcurrentConsumers);

        return new DisruptorEndpoint(uri, this, bufferSize, concurrentConsumers);
    }

    public int getDefaultBufferSize() {
        return defaultBufferSize;
    }

    public void setDefaultBufferSize(int defaultBufferSize) {
        this.defaultBufferSize = defaultBufferSize;
    }

    public int getDefaultConcurrentConsumers() {
        return defaultConcurrentConsumers;
    }

    public void setDefaultConcurrentConsumers(int defaultConcurrentConsumers) {
        this.defaultConcurrentConsumers = defaultConcurrentConsumers;
    }
}
