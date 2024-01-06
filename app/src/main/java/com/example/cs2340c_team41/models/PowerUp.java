package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

public interface PowerUp {
    //method all implementing classes must use
    void updatePlayerAttributes(PlayerViewModel playerViewModel);
}
