import React, {useEffect, useState} from 'react';
import './ProfilePage.scss';
import {TicketUpgrade} from "../../enum/TicketUpgrade";
import {Addon} from "../../enum/Addon";
import Navbar from "../NavbarComponent/Navbar";
import { useAuth } from '../../hooks/useAuth';
import { PawnItem } from '../../model/PawnItem';
import ItemCard from '../MovieCard/MovieCard';
import PawnshopService from '../../services/pawnItemService';

const ProfilePage: React.FC = () => {
    const {authUser} = useAuth();
    const [pawnItems, setPawnItems] = useState<PawnItem[]|null>()

    useEffect(()=>{
        if(authUser){
            const pawnItemService = new PawnshopService();
            pawnItemService.getAllByUserId(authUser!.id).then(data =>
                {
                    if(data){
                        setPawnItems(data);
                    }
                }
            );
        }
    },[authUser])

    if (pawnItems == null){
        return <div>Loading...</div>
    }

    return (
        <div className={"page"}>
            <Navbar title={"Profile"}/>
            <div className={'movies-grid'}>
                {pawnItems.map(item => (
                    <ItemCard key={item.id} item={item} deletable={true}/>
                ))}
            </div>
        </div>
    )
};

export default ProfilePage;