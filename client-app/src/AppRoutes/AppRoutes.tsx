import React, { FC } from 'react';
import './AppRoutes.scss';
import {Route, Routes} from "react-router-dom";
import AuthRequired from "../components/AuthRequired/AuthRequired";
import AuthPage from "../components/AuthPage/AuthPage";
import AuthAdminRequired from "../components/AuthAdminRequiredComponent/AuthAdminRequiredComponent";
import TicketCreatePage from "../components/TickedCreatePage/TicketCreatePage";
import TicketUpgradePage from "../components/TicketUpgradePage/TicketUpgradePage";
import MoviePage from "../components/MoviePageComponent/MoviePage";
import MoviesPage from "../components/MoviesPageComponent/MoviesPage";
import AuthContext, {AuthProvider} from "../contexts/authContext";
import MovieCreate from "../components/MovieCreate/MovieCreate";
import ProfilePage from "../components/ProfilePageComponent/ProfilePage";

interface AppRoutesProps {}


const AppRoutes: FC<AppRoutesProps> = () => {
    return (
        <AuthProvider>
        <Routes>
            <Route element={<AuthAdminRequired/>}>
                <Route path={"/create-movie"} element={<MovieCreate/>}></Route>
            </Route>
            <Route path={"/movies"} element={<MoviesPage/>}></Route>
            <Route path={"/movie/:movieId"} element={<MoviePage/>}></Route>
            <Route element={<AuthRequired/>}>
                <Route path={"/create-ticket/:movieId"} element={<TicketCreatePage/>}></Route>
            </Route>
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
