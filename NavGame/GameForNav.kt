package com.exercise.luminus_project.NavGame

sealed class GameForNav(val route : String) {
    object GameScreen: GameForNav("game_screen")
    object GameMenu: GameForNav("game_menu")
    object MainMenu : GameForNav("main_menu")
    object SelectScreen : GameForNav("select_screen")
    object RewardScreen : GameForNav("reward_screen")
}