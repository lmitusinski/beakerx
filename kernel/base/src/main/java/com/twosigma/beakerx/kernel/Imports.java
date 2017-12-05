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
package com.twosigma.beakerx.kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.twosigma.beakerx.kernel.AddImportStatus.ADDED;
import static com.twosigma.beakerx.kernel.AddImportStatus.EXISTS;

public class Imports {

  private List<ImportPath> imports = new ArrayList<>();
  private List<String> importsAsStrings = null;

  public List<ImportPath> getImportPaths() {
    return imports;
  }

  public AddImportStatus add(ImportPath anImport) {
    checkNotNull(anImport);
    if (this.imports.contains(anImport)) {
      return EXISTS;
    }
    clear();
    this.imports.add(anImport);
    return ADDED;
  }

  public boolean remove(ImportPath anImport) {
    checkNotNull(anImport);
    if (this.imports.contains(anImport)) {
      clear();
      return this.imports.remove(anImport);
    }
    return false;
  }

  public boolean isEmpty() {
    return imports.isEmpty();
  }

  public List<String> toListOfStrings() {
    if (importsAsStrings == null) {
      this.importsAsStrings = importsToStrings();
    }
    return this.importsAsStrings;
  }

  private List<String> importsToStrings() {
    List<String> importsAsStrings = new ArrayList<>();
    for (ImportPath st : getImportPaths()) {
      importsAsStrings.add(st.asString());
    }
    return importsAsStrings;
  }

  private void clear() {
    this.importsAsStrings = null;
  }

  @Override
  public String toString() {
    return imports.stream().map(ImportPath::asString).collect(Collectors.joining("\n"));
  }
}
