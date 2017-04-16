/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxgl.app.state

import com.almasb.fxgl.app.ApplicationState
import com.almasb.fxgl.app.FXGL
import com.almasb.fxgl.app.InitAppTask
import com.almasb.fxgl.saving.DataFile
import com.almasb.fxgl.scene.LoadingScene
import javafx.concurrent.Task

/**
 *
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
object LoadingState : AbstractAppState(FXGL.getApp().sceneFactory.newLoadingScene()) {

    lateinit var initTask: InitAppTask

    override fun onEnter(prevState: State) {
        when(prevState) {
            is StartupState -> {

            }

            is IntroState -> {

            }

            is MainMenuState -> {
                // if load or new game?
                FXGL.getApp().reset()
            }

            is GameMenuState -> {
                FXGL.getApp().reset()
            }

            is PlayState -> {
                FXGL.getApp().reset()
            }

            else -> throw IllegalArgumentException("Entered LoadingState from illegal state: $prevState")
        }

        initTask.onEndAction = Runnable {
            FXGL.getApp().setState(ApplicationState.PLAYING)
        }

        (scene() as LoadingScene).bind(initTask)

        FXGL.getExecutor().execute(initTask)
    }

    override fun onExit() {
    }

    override fun onUpdate(tpf: Double) {
    }
}