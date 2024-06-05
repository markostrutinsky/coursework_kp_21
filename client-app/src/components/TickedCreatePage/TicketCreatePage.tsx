import React, {useEffect, useState} from 'react';
import './TicketCreatePage.scss';
import TicketService from '../../services/ticketService';
import {useParams} from "react-router-dom";
import {MovieDTO} from "../../models/movieDTOs/MovieDTO";
import MovieService from "../../services/movieService";
import {SeatDTO} from "../../models/seatDTOs/SeatDTO";
import Navbar from "../NavbarComponent/Navbar"; // Adjust the import path

interface TicketCreatePageProps {
    // Define any props if needed, such as initial data or callback functions
}

const TicketCreatePage: React.FC<TicketCreatePageProps> = () => {
    const movieId = useParams<{ movieId: string }>().movieId;
    const [ticketDetails, setTicketDetails] = useState({
        seatId: "",
        movieDate: new Date(),
    });
    
    const [movie, setMovie] = useState({} as MovieDTO); // Add state for seats
    const [rowsColumns, setRowsColumns] = useState({ rows: 0, columns: 0 }); // Add state for rows and columns
    
    const [selectedSeat, setSelectedSeat] = useState({} as SeatDTO); // Add state for selected seat
    
    useEffect(() => {
        const movieService = new MovieService();
        movieService.getMovieInfo(movieId || "").then((response) => {
            setMovie(response);
            const maxRow = Math.max(...response.seats.map((seat: SeatDTO) => seat.rowNumber));
            const maxColumn = Math.max(...response.seats.map((seat: SeatDTO) => seat.seatNumber));
            setRowsColumns({
                rows: maxRow,
                columns: maxColumn,
            });
        });
    }, [movieId]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const ticketService = new TicketService();
        await ticketService.bookSeat(movieId || "", ticketDetails);
    };


    function selectSeat(number: number, number2: number) {
        const seat = movie.seats.find((seat: SeatDTO) => seat.rowNumber == number && seat.seatNumber == number2);
        if (seat) {
            console.log(seat.id);
            setSelectedSeat(seat);
            setTicketDetails({
                ...ticketDetails,
                seatId: seat.id,
            });
        }
    }

    function submitTicket() {
        const ticketService = new TicketService();
        ticketService.bookSeat(movieId || "", ticketDetails).then((response) => {
            if (response) {
                alert("Ticket created successfully");
            } else {
                alert("Ticket creation failed");
            }
        });
    }

    return (
        <div className="page centered">
            <Navbar title={"Create new ticket for " + movie.name}/>
            <form className="form" onSubmit={handleSubmit}>
                {[...Array(rowsColumns.rows)].map((_, rowIndex) => (
                    <div className="row" key={rowIndex}>
                        {[...Array(rowsColumns.columns)].map((_, columnIndex) => (
                            <div className={"column seat " + ((selectedSeat.rowNumber == rowIndex + 1 && selectedSeat.seatNumber == columnIndex + 1) ? "selected" : "")} key={columnIndex} onClick={() => selectSeat(rowIndex+1, columnIndex+1)}>
                                {rowIndex + 1} {columnIndex + 1}
                            </div>
                        ))}
                    </div>
                ))}
                <button className="button" onClick={submitTicket}>Create Ticket</button>
            </form>
        </div>
    );
};

export default TicketCreatePage;