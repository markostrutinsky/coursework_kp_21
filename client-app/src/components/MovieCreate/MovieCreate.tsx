import React, { useState } from 'react';
import './MovieCreate.scss';
import MovieService from '../../services/movieService';
import Navbar from "../NavbarComponent/Navbar"; // Adjust the import path

const MovieCreate: React.FC = () => {
    const [movieDetails, setMovieDetails] = useState({
        name: '',
        genre: '',
        filmDuration: '',
        director: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setMovieDetails({
            ...movieDetails,
            [name]: value,
        });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const movieService = new MovieService();
        await movieService.addMovie(movieDetails);
        // Reset form or provide feedback
    };

    return (
        <div className={"page"}>
            <Navbar title={"Create new movie"}/> 
            <form className={"form"} onSubmit={handleSubmit}>
                <div className={"formGroup"}>
                    <label className={"label"} htmlFor="name">Name</label>
                    <input
                        className={"input"}
                        type="text"
                        id="name"
                        name="name"
                        value={movieDetails.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={"formGroup"}>
                    <label className={"label"} htmlFor="genre">Genre</label>
                    <input
                        className={"input"}
                        type="text"
                        id="genre"
                        name="genre"
                        value={movieDetails.genre}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={"formGroup"}>
                    <label className={"label"} htmlFor="filmDuration">Film Duration</label>
                    <input
                        className={"input"}
                        type="text"
                        id="filmDuration"
                        name="filmDuration"
                        value={movieDetails.filmDuration}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={"formGroup"}>
                    <label className={"label"} htmlFor="director">Director</label>
                    <input
                        className={"input"}
                        type="text"
                        id="director"
                        name="director"
                        value={movieDetails.director}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button className={"button"} type="submit">Add Movie</button>
            </form>
        </div>
    );
};

export default MovieCreate;