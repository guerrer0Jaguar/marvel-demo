const React = require('react'); 
const ReactDOM = require('react-dom'); 
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {characters: [], characterSelected: {}};		
		this.onDetail = this.onDetail.bind(this);
	}

	componentDidMount() { 
		client({method: 'GET', path: 'http://localhost:8080/api/characters'}).done(response => {
			this.setState({characters: response.entity.data.results});
		});
	}

	render() { 
		return (
			<div>
				<CharacterList 
					characters={this.state.characters}
					onDetail={this.onDetail}>			
				</CharacterList>
				<CharacterDetail character={this.state.characterSelected}/>
			</div>
		)
	}
	
	onDetail(character) {
		let uri = 'http://localhost:8080/api/characters/' + character.id;
		client({method: 'GET', path: uri}).done(response => {			
			console.log(response.entity.data);	
			let elem = response.entity.data.results[0]; 				
			this.setState( {characterSelected: elem});		
		});
	}		
}

class CharacterList extends React.Component{
	render() {
		const characters = this.props.characters.map(character =>
			<Character 
				key={character.id} 
				character={character}
				onDetail={this.props.onDetail}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
					</tr>
					{characters}
				</tbody>
			</table>
		)
	}
}

class Character extends React.Component{
	constructor(props) {
		super(props);
		this.handleDetail = this.handleDetail.bind(this);
	}

	handleDetail() {		
		this.props.onDetail(this.props.character);
	}
	
	render() {
		return (
			<tr>
				<td><a href="#characterDetail" onClick={this.handleDetail}>{this.props.character.id} </a></td>
				<td>{this.props.character.name}</td>
				<td>{this.props.character.description}</td>
			</tr>
		)
	}
}

class CharacterDetail extends React.Component {
	constructor(props) {
		super(props);		
	}
	
	render() {
		return (
			<div>							
				<div id="characterDetail" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>
						<h2></h2>
						<form>
							<h2>{this.props.character.id}</h2>				
						</form>
					</div>
				</div>
			</div>
		)
	}
	
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)