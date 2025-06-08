package com.app.guimscore.view.controller;

import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list")
public class ListItemController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private ListItem listItem;



}
