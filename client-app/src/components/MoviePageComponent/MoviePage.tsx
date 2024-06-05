import React, {FC, useEffect, useState} from "react";
import {createApi} from "unsplash-js";
import {useNavigate, useParams} from "react-router-dom";
import {MovieDTO} from "../../models/movieDTOs/MovieDTO";
import MovieService from "../../services/movieService";
import './MoviePage.scss';
import Navbar from "../NavbarComponent/Navbar";
import {SeatDTO} from "../../models/seatDTOs/SeatDTO";

interface MoviePageComponentProps {
    
}

const MoviePage: React.FC = () => {
    const { movieId } = useParams<{ movieId: string }>();
    const [movie, setMovie] = useState<MovieDTO | null>(null);
    const movieService = new MovieService();
    const navigate = useNavigate();
    const [rowsColumns, setRowsColumns] = useState({} as { rows: number, columns: number });
    const [availableSeats, setAvailableSeats] = useState([] as SeatDTO[]);
    
    const unsplash = createApi({
        accessKey: "r8qFrOZh0tIDcedTPU5l6OlkIJuiQ7rx56ngqf0fxZc",
    });
    
    useEffect(() => {
        if (movieId) {
            console.log(movieId)
            movieService.getMovieInfo(movieId).then((data) => {
                console.log(data);
                rowsColumns.rows = Math.max(...data.seats.map((seat: SeatDTO) => seat.rowNumber));
                rowsColumns.columns = Math.max(...data.seats.map((seat: SeatDTO) => seat.seatNumber));
                setMovie(data);
            });
            movieService.getSeatsByMovieId(movieId).then((data) => {
                console.log(data);
                setAvailableSeats(data);
            });
        }
    }, []);

    useEffect(() => {
        if(movie){
            unsplash.search.getPhotos({
                query: movie.name,
                perPage: 1,
                page: 1
            }).then(result => {
                console.log(result.response?.results[0].urls.small);
                document.querySelector(".movieImage")!.setAttribute("src", result.response?.results[0].urls.small || "");
            });
        }
    }, [movie]);
    
    if (!movie) {
        return <div className={"page"}>Loading...</div>;
    }

    function handleBook() {
        navigate(`/create-ticket/${movieId}`);
    }
    
    const isSeatAvailable = (row: number, column: number) => {
        return availableSeats.some(seat => seat.rowNumber == row && seat.seatNumber == column);
    }

    return <div>
        <div className={"page"}>
            <Navbar title={"Movie: " + movie.name}/>
            <div className={"movieCard"}>
                <div style={{width: "100%", display:"flex", justifyContent:"center"}}><img className={"movieImage"} src="/" alt={movie.name} /></div>
                <h2 className={"title"}>{movie.name} <button className={"button"} onClick={handleBook}>Book a ticket</button></h2>
                <p className={"genre"}>{movie.genre}</p>
                <p>Duration: {movie.filmDuration}</p>
                <p>Directed by: {movie.director}</p>
            </div>
            {availableSeats.length != 0 ? <div>
                <div>Seat availability:
                    {[...Array(rowsColumns.rows)].map((_, rowIndex) => (
                        <div className="row" key={rowIndex}>
                            {[...Array(rowsColumns.columns)].map((_, columnIndex) => (
                                <div
                                    className={"column seat " + (isSeatAvailable(rowIndex, columnIndex) ? "available" : "unavailable")}
                                    key={columnIndex}>
                                    {rowIndex + 1} {columnIndex + 1}
                                </div>
                            ))}
                        </div>
                    ))}
                </div>
            </div> : null}
        </div>
    </div>;
};

export default MoviePage;