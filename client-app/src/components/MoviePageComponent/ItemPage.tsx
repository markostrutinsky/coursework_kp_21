import React, {FC, useEffect, useState} from "react";
import {createApi} from "unsplash-js";
import {useNavigate, useParams} from "react-router-dom";
import './MoviePage.scss';
import Navbar from "../NavbarComponent/Navbar";
import {PawnItem} from "../../model/PawnItem";
import PawnItemService from "../../services/pawnItemService";
import { Exception } from "sass";

interface MoviePageComponentProps {
    
}

const ItemPage: React.FC = () => {
    const { itemId } = useParams<{ itemId: string }>();
    const [pawnItem, setPawnItem] = useState<PawnItem | null>(null);
    const navigate = useNavigate();
    const pawnItemService = new PawnItemService();
    
    
    useEffect(() => {
        if (itemId) {
            console.log(itemId);
            pawnItemService.getAll().then((data) => {
                console.log(data);
                const item = data.find(i => i.id == parseInt(itemId))
                if(item){
                    setPawnItem(item)
                }else{
                    throw new Error("no item found");
                }
            })
        }
    }, []);
    
    if (!pawnItem) {
        return <div className={"page"}>Loading...</div>;
    }

    return <div>
        <div className={"page"}>
            <Navbar title={"Item: " + pawnItem.pawnItemName}/>
            <div className={"movieCard"}>
                <div style={{width: "100%", display:"flex", justifyContent:"center"}}><img className={"movieImage"} src="/" alt={pawnItem.pawnItemName} /></div>
                <p className={"genre"}>{pawnItem.category}</p>
            </div>
        </div>
    </div>;
};

export default ItemPage;