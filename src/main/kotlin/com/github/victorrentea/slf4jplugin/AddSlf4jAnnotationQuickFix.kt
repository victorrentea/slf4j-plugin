package com.github.victorrentea.slf4jplugin

import com.intellij.codeInsight.intention.impl.BaseIntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil

data class AddSlf4jAnnotationQuickFix(val logExpression: PsiReferenceExpression) : BaseIntentionAction() {
    override fun getFamilyName(): String {
        return "Victor"
    }

    override fun getText(): String {
        return "Add @Slf4j to class"
    }

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        return true;
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        if (editor == null || file == null) {
            return;
        }
        val parentClass = PsiTreeUtil.getTopmostParentOfType(logExpression, PsiClass::class.java) ?: return
        //        println("Found class : " + parentClass + " containing " + logExpression)

        val elementFactory = JavaPsiFacade.getInstance(project).getElementFactory()
        val annotation = elementFactory.createAnnotationFromText("@lombok.extern.slf4j.Slf4j", parentClass)


        file.addBefore(annotation, parentClass)
        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.document)
        JavaCodeStyleManager.getInstance(project).shortenClassReferences(parentClass)
    }

}
