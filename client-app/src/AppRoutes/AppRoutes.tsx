import React, { FC } from 'react';
import './AppRoutes.scss';
import {Route, Routes} from "react-router-dom";
import AuthRequired from "../components/AuthRequired/AuthRequired";
import AuthPage from "../components/AuthPage/AuthPage";
import ItemPage from '../components/MoviePageComponent/ItemPage';
import ItemsPage from '../components/MoviesPageComponent/ItemsPage';
import ProfilePage from '../components/ProfilePageComponent/ProfilePage';
import { AuthProvider } from '../contexts/authContext';
import ItemCreate from "../components/MovieCreate/MovieCreate";

interface AppRoutesProps {}


const AppRoutes: FC<AppRoutesProps> = () => {
    return (
        <AuthProvider>
        <Routes>
            <Route path={"/add-item"} element={<ItemCreate/>}></Route>
            <Route path={"/items"} element={<ItemsPage/>}></Route>
            <Route path={"/item/:itemId"} element={<ItemPage/>}></Route>
            <Route element={<AuthRequired/>}>
                <Route path={"/profile"} element={<ProfilePage/>}></Route>
            </Route>
            <Route path={"/"} element={<AuthPage/>}></Route>
            <Route path={"/auth-page"} element={<AuthPage/>}></Route>
        </Routes>
        </AuthProvider>
    )
}

export default AppRoutes;
