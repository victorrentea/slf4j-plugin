package com.github.victorrentea.slf4jplugin

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceExpression

class Slf4jAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is PsiReferenceExpression || element.text != "log") {
            return
        }
        if (element.resolve() != null) {
            return
        }

        holder.newAnnotation(HighlightSeverity.ERROR, "Add @Slf4j to class")
            .range(element.textRange)
            .tooltip("@Slf4j")
            .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL) // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
            .withFix(AddSlf4jAnnotationQuickFix(element))
            .create()
    }

}