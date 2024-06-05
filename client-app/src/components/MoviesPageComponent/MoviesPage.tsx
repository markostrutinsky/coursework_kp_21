import React, {FC, useEffect, useState} from "react";
import {MovieDTO} from "../../models/movieDTOs/MovieDTO";
import MovieService from "../../services/movieService";
import './MoviesPage.scss';
import MovieCard from "../MovieCard/MovieCard";
import Navbar from "../NavbarComponent/Navbar";

interface MoviesPageComponentProps {
        
}

const MoviesPage: FC<MoviesPageComponentProps> = (props) => {
    const [movies, setMovies] = useState<MovieDTO[]>([])
    const movie : MovieService = new MovieService();
    useEffect(() => {
        movie.getAllMovies().then((data) => {
            console.log(data);
            setMovies(data);
        })
    }, [])

    return (
        <div className={"page"}>
            <Navbar title={"Movies"}/>
            <div className={'movies-grid'}>
                {movies.map(movie => (
                    <MovieCard key={movie.id} movie={movie} />
                ))}
            </div>
        </div>
    );
}

export default MoviesPage;