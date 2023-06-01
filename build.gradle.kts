// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val androidGradlePlugin by extra("8.0.2")
    val androidKotlinPlugin by extra("1.8.20")
}

plugins {
    id("com.android.application") version "${extra["androidGradlePlugin"]}" apply false
    id("com.android.library") version "${extra["androidGradlePlugin"]}" apply false
    id("org.jetbrains.kotlin.android") version "${extra["androidKotlinPlugin"]}" apply false
}