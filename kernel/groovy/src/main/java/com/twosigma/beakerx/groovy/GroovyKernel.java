/*
 *  Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.twosigma.beakerx.groovy;

import com.twosigma.beakerx.evaluator.Evaluator;

import com.twosigma.beakerx.groovy.comm.GroovyCommOpenHandler;
import com.twosigma.beakerx.groovy.evaluator.GroovyEvaluator;
import com.twosigma.beakerx.groovy.handler.GroovyKernelInfoHandler;
import com.twosigma.beakerx.jupyter.handler.CommOpenHandler;
import com.twosigma.beakerx.kernel.ConfigurationFile;
import com.twosigma.beakerx.kernel.KernelConfigurationFile;
import com.twosigma.beakerx.kernel.KernelRunner;
import com.twosigma.beakerx.kernel.KernelSocketsFactory;
import com.twosigma.beakerx.kernel.KernelSocketsFactoryImpl;
import com.twosigma.beakerx.handler.KernelHandler;
import com.twosigma.beakerx.message.Message;
import com.twosigma.beakerx.kernel.Kernel;

import java.io.IOException;

import static com.twosigma.beakerx.jupyter.Utils.uuid;

public class GroovyKernel extends Kernel {

  public GroovyKernel(final String id, final Evaluator evaluator, KernelSocketsFactory kernelSocketsFactory) {
    super(id, evaluator, kernelSocketsFactory);
  }

  @Override
  public CommOpenHandler getCommOpenHandler(Kernel kernel) {
    return new GroovyCommOpenHandler(kernel);
  }

  @Override
  public KernelHandler<Message> getKernelInfoHandler(Kernel kernel) {
    return new GroovyKernelInfoHandler(kernel);
  }

  public static void main(final String[] args) throws InterruptedException, IOException {
    KernelRunner.run(() -> {
      String id = uuid();
      KernelSocketsFactoryImpl kernelSocketsFactory = new KernelSocketsFactoryImpl(new KernelConfigurationFile(args));
      return new GroovyKernel(id, new GroovyEvaluator(id, id), kernelSocketsFactory);
    });
  }

}