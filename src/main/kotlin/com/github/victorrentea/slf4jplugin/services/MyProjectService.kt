package com.github.victorrentea.slf4jplugin.services

import com.intellij.openapi.project.Project
import com.github.victorrentea.slf4jplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
