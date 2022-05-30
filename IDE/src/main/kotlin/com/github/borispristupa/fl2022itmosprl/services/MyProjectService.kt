package com.github.borispristupa.fl2022itmosprl.services

import com.intellij.openapi.project.Project
import com.github.borispristupa.fl2022itmosprl.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
