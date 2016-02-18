/*
 * Copyright (c) 2015. See AUTHORS file.
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

package com.mbrlabs.mundus.ui.modules;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.Tooltip;
import com.mbrlabs.mundus.core.Inject;
import com.mbrlabs.mundus.core.Mundus;
import com.mbrlabs.mundus.core.project.ProjectContext;
import com.mbrlabs.mundus.core.project.ProjectManager;
import com.mbrlabs.mundus.tools.ToolManager;
import com.mbrlabs.mundus.ui.Ui;
import com.mbrlabs.mundus.ui.widgets.FaTextButton;
import com.mbrlabs.mundus.ui.widgets.ToggleButton;
import com.mbrlabs.mundus.ui.widgets.Toolbar;
import com.mbrlabs.mundus.utils.Fa;

/**
 * @author Marcus Brummer
 * @version 24-11-2015
 */
public class MundusToolbar extends Toolbar {

    private FaTextButton saveBtn;
    private FaTextButton importBtn;
    private FaTextButton exportBtn;

    private FaTextButton selectBtn;
    private FaTextButton translateBtn;
    private FaTextButton rotateBtn;

    private ToggleButton globalLocalSwitch;

    @Inject
    private ToolManager toolManager;
    @Inject
    private ProjectManager projectManager;
    @Inject
    private ProjectContext projectContext;

    public MundusToolbar() {
        super();
        Mundus.inject(this);
        saveBtn = new FaTextButton(Fa.SAVE);
        saveBtn.padRight(7).padLeft(7);
        new Tooltip(saveBtn, "Save project");

        importBtn = new FaTextButton(Fa.DOWNLOAD);
        importBtn.padRight(7).padLeft(7);
        new Tooltip(importBtn, "Import model");

        exportBtn = new FaTextButton(Fa.GIFT);
        exportBtn.padRight(12).padLeft(7);
        new Tooltip(exportBtn, "Export project");

        selectBtn = new FaTextButton(toolManager.selectionTool.getIconFont());
        selectBtn.padRight(7).padLeft(12);
        new Tooltip(selectBtn, toolManager.selectionTool.getName());

        translateBtn = new FaTextButton(toolManager.translateTool.getIconFont());
        translateBtn.padRight(7).padLeft(7);
        new Tooltip(translateBtn, toolManager.translateTool.getName());

        rotateBtn = new FaTextButton(Fa.REFRESH);
        rotateBtn.padRight(7).padLeft(7);
        new Tooltip(rotateBtn, "Rotate Tool");

        globalLocalSwitch = new ToggleButton("Global space", "Local space");

        addItem(saveBtn, true);
        addItem(importBtn, true);
        addItem(exportBtn, true);
        addSeperator(true);
        addItem(selectBtn, true);
        addItem(translateBtn, true);
        addItem(rotateBtn, true);
        addSeperator(true);
        // TODO add back in, once i implemented proper ray picking for rotated objects
        //addItem(globalLocalSwitch, true);

        // save btn
        saveBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                projectManager.saveProject(projectContext);
            }
        });

        // export btn
        exportBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Ui ui = Ui.getInstance();
                ui.showDialog(ui.getExportDialog());
            }
        });

        // import btn
        importBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Ui ui = Ui.getInstance();
                ui.showDialog(ui.getImportDialog());
            }
        });

        // select tool
        selectBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toolManager.activateTool(toolManager.selectionTool);
            }
        });

        // translate tool
        translateBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toolManager.activateTool(toolManager.translateTool);
            }
        });

        // global / local space switching
        globalLocalSwitch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toolManager.translateTool.setGlobalSpace(globalLocalSwitch.isOn());
            }
        });

    }

    public FaTextButton getSaveBtn() {
        return saveBtn;
    }

    public FaTextButton getImportBtn() {
        return importBtn;
    }

}
