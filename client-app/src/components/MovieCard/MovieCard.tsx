import React from 'react';
import {MovieDTO} from '../../models/movieDTOs/MovieDTO';
import './MovieCard.scss';
import {useNavigate} from "react-router-dom";
import MovieService from "../../services/movieService";

interface MovieCardProps {
    movie: MovieDTO;
}

const MovieCard: React.FC<MovieCardProps> = ({ movie }) => {
    const isAdmin = JSON.parse(localStorage.getItem('authUser')!).user.isAdmin;
    const movieService = new MovieService();
    
    const navigate = useNavigate();
    function handleRedirect() {
        navigate(`/movie/${movie.id}`);
    }

    function handleTicketBookingRedirect(e: React.MouseEvent<HTMLButtonElement>) {
        e.stopPropagation();
        navigate(`/create-ticket/${movie.id}`);
    }

    function deleteMovie() {
        movieService.removeMovie(movie.id).then(() => {
            navigate('/movies', {replace: true});
        });
    }

    return (
        <div className={"card"} onClick={handleRedirect}>
            <h2 className={"title"}>{movie.name}</h2>
            <p className={"genre"}>{movie.genre}</p>
            <p>Directed by: {movie.director}</p>
            <p>Duration: {movie.filmDuration}</p>
            <button className={"button"} onClick={e => handleTicketBookingRedirect(e)}>Book a ticket</button>
            {isAdmin ? <button className={"button delete-button"} onClick={deleteMovie}>Remove</button> : null}
        </div>
    );
};

export default MovieCard;