/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.controller.DbConnection;

/**
 *
 * @author pc
 */
public interface DatabaseVariable {
    public static final DbConnection db = new DbConnection();
}
