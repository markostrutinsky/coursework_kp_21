import React, {useEffect, useState} from 'react';
import './ProfilePage.scss';
import TicketService from "../../services/ticketService";
import {TicketDTO} from "../../models/ticketDTOs/TicketDTO";
import {TicketUpgrade} from "../../enum/TicketUpgrade";
import {Addon} from "../../enum/Addon";
import Navbar from "../NavbarComponent/Navbar";

const ProfilePage: React.FC = () => {
    const ticketService = new TicketService();
    const [tickets, setTickets] = useState([] as TicketDTO[]);
    const [ticketOpenId, setTicketOpenId] = useState(null as string | null);
    
    useEffect(() => {
        ticketService.getMyTickets().then((response) => {
           setTickets(response); 
        });
    }, []);

    if (!tickets) {
        return <div className="pp-page">Loading...</div>;
    }

    function handleUpgradeClick(ticketId: string) {
        setTicketOpenId(ticketId);
    }

    function upgrade(upgradeType: TicketUpgrade, id: string) {
        ticketService.upgradeTicket(id, upgradeType).then((response) => {
            if (response) {
                ticketService.getMyTickets().then((response) => {
                    setTickets(response);
                    setTicketOpenId(null);
                });
            }
        });
    }

    function deleteTicket(id: string) {
        ticketService.deleteTicket(id).then(() => {
            ticketService.getMyTickets().then((response) => {
                setTickets(response);
            });
        });
    }

    return (<div>
        <div className="page">
            <Navbar title={"My tickets"}/> 
            <div className={"pp-page"}>
            {tickets.map((ticket) => {
                return (
                    <div key={ticket.id} className="pp-ticket">
                        <h1>{ticket.movie.name}</h1>
                        {ticket.addons.map((addon) => {
                            return <p key={addon}>{Addon[addon]}</p>;
                        })}
                        <p>{ticket.movieDate.toString()}</p>
                        <button className={"button"} onClick={() => handleUpgradeClick(ticket.id)}>Upgrade ticket</button>
                        <button className={"button delete-button"} onClick={() => deleteTicket(ticket.id)}>Delete ticket</button>
                        {ticket.id != ticketOpenId ? null : <p>Choose upgrade:
                        <button className={"button"} onClick={() => upgrade(TicketUpgrade.VIP, ticket.id)}>VIP</button>
                        <button className={"button"} onClick={() => upgrade(TicketUpgrade.Standard, ticket.id)}>Standard</button>
                        <button className={"button"} onClick={() => upgrade(TicketUpgrade.Luxe, ticket.id)}>Luxe</button>
                        <button className={"button"} onClick={() => upgrade(TicketUpgrade.Hungry, ticket.id)}>Hungry</button>
                        </p>}
                    </div>
                );
            })}
            </div> 
        </div>
    </div>
    );
};

export default ProfilePage;