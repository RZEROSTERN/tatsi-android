// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val androidGradlePlugin by extra("8.0.2")
    val androidKotlinPlugin by extra("1.8.20")
    val gmsGoogleServices by extra("4.3.15")
    val coreKtx by extra("1.8.0")
    val hilt by extra("2.44")
    val kapt by extra("1.8.21")

    dependencies {
        classpath("com.google.gms:google-services:$gmsGoogleServices")
    }
}

plugins {
    id("com.android.application") version "${extra["androidGradlePlugin"]}" apply false
    id("com.android.library") version "${extra["androidGradlePlugin"]}" apply false
    id("org.jetbrains.kotlin.android") version "${extra["androidKotlinPlugin"]}" apply false
    id("com.google.dagger.hilt.android") version "${extra["hilt"]}" apply false
    kotlin("kapt") version "${extra["kapt"]}" apply false
}