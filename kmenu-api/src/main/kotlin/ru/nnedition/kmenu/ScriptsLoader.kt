package ru.nnedition.kmenu

import ru.nnedition.kmenu.menu.Menu
import java.io.File
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.*
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class ScriptsLoader(
    val scriptsFolder: File
) {
    private val menus: MutableMap<String, Menu> = HashMap()
    fun getMenu(id: String) = menus[id]
    fun getMenus() = menus.toMap()

    fun load() {
        this.scriptsFolder.listFiles()?.forEach {
            if (it.extension != "kts") return@forEach

            load(it)
        }
    }

    fun load(file: File) {
        loadMenuFromScript(file)?.let { menu -> this.menus[menu.id] = menu }
    }


    private fun loadMenuFromScript(scriptFile: File): Menu? {
        val scriptSource = scriptFile.toScriptSource()

        val compilationConfiguration = ScriptCompilationConfiguration {
            jvm {
                dependenciesFromClassContext(Menu::class, wholeClasspath = true)
            }
        }

        val evaluationConfiguration = ScriptEvaluationConfiguration {
            jvm {
                baseClassLoader(this::class.java.classLoader)
            }
        }

        val scriptingHost = BasicJvmScriptingHost()
        val result = scriptingHost.eval(scriptSource, compilationConfiguration, evaluationConfiguration)

        val value = result.valueOrNull().asSuccess().value ?: return null

        val clazz = value.returnValue.scriptClass ?: return null
        val instance = value.returnValue.scriptInstance

        return clazz.java.getMethod("create").invoke(instance) as? Menu
    }
}