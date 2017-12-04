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

import createTableContextMenuItems from './createTableMenuItems';
import createTableCellMenuItems from './createTableCellMenuItems';
import BkoContextMenu from '../../contextMenu/BkoContextMenu';

export default class TableContextMenu extends BkoContextMenu {
  constructor(scope: any) {
    super(scope);
  }

  protected buildMenu(): void {
    this.inLab ? this.buildLabMenu() : this.buildBkoMenu();

    const menuItems = createTableContextMenuItems(this.scope);
    const cellMenuItems = createTableCellMenuItems(this.scope, this.scope.model.getCellModel());
    this.createItems([ ...menuItems, ...cellMenuItems ], this.contextMenu);
    this.bindEvents();
  }
}
