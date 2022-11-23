import SandwichDetails from "./SandwichDetails";
import {Sandwich} from "../model/Sandwich";
import "./SandwichOverview.css"
import {Link} from "react-router-dom";

type SandwichOverviewProps = {
    sandwiches: Sandwich[];
    deleteSandwich: (id: string) => void;
}

export default function SandwichOverview(props: SandwichOverviewProps) {

    return (
        <div className="menu-list">
            {props.sandwiches.map((sandwich) =>
                <Link className={"Link"} to={"/sandwiches/" + sandwich.id}>
                    <SandwichDetails key={sandwich.id} sandwich={sandwich}
                                     deleteSandwich={props.deleteSandwich}/></Link>)}
        </div>
    )

}