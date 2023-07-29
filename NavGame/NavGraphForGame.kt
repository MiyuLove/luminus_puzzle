package com.exercise.luminus_project.NavGame

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraphGame(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = GameForNav.MainMenu.route)
    {
        composable(route = GameForNav.MainMenu.route){
            MainScreen(navController)
        }
        composable(route = GameForNav.SelectScreen.route){
            SelectScreen(navController)
        }
        composable(route = GameForNav.GameScreen.route){
            GamePlayScreen(navController)
        }
        composable(route = GameForNav.GameMenu.route){
            GameRewardScreen(navController)
        }
        composable(route = GameForNav.RewardScreen.route){
            RewardScreen()
        }
    }
}