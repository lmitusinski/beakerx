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
package com.twosigma.beakerx.jvm.classloader;

import com.twosigma.beakerx.kernel.PathToJar;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class BeakerxUrlClassLoader extends URLClassLoader {

  public BeakerxUrlClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
  }

  public BeakerxUrlClassLoader(ClassLoader parent) {
    super(new URL[0], parent);
  }

  public void addJar(URL url) {
    super.addURL(checkNotNull(url));
  }

  public void addJar(PathToJar pathToJar) {
    super.addURL(checkNotNull(pathToJar.getUrl()));
  }

  public void addJars(List<String> paths) {
    for (String dir : paths) {
      super.addURL(new PathToJar(dir).getUrl());
    }
  }

  public static List<URL> createUrls(List<String> dirs) {
    List<URL> urlList = new ArrayList<>();
    for (String dir : dirs) {
      urlList.add(new PathToJar(dir).getUrl());
    }
    return urlList;
  }
}
