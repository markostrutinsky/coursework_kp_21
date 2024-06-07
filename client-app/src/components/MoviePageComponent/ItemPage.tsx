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
                <div>Properties: </div>
                {Object.entries(pawnItem).map(entry =>{
                    if(entry[0] == "agreement" || entry[0] == "user" || entry[0] == "photo" || entry[0] == "preciousStones" || entry[0] == "soldTo")
                    {
                        return null;
                    }
                    else
                    {
                        return <div>{entry[0]} : {entry[1]}</div>
                    }
                })}
                <div style={{marginTop:"10px"}}>Agreement: 
                    {
                        Object.entries((pawnItem as any).agreement).map(entry =>{
                            if(entry[0] == "paid")
                            {
                                return null;
                            }
                            else
                            {
                                return <div style={{marginLeft:"30px"}}> {entry[0]} : {entry[1] as string}</div>
                            }
                        })
                    }
                </div>
            </div>
        </div>
    </div>;
};

export default ItemPage;