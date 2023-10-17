const React = require('react'); 
const ReactDOM = require('react-dom'); 
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {characters: []};
		this.onDetail = this.onDetail.bind(this);
	}

	componentDidMount() { 
		client({method: 'GET', path: 'http://localhost:8080/api/characters'}).done(response => {
			this.setState({characters: response.entity.data.results});
		});
	}

	render() { 
		return (
			<CharacterList 
				characters={this.state.characters}
				onDetail={this.onDetail}/>
		)
	}
	
	onDetail(character) {
		let uri = 'http://localhost:8080/api/characters/' + character.id;
		client({method: 'GET', path: uri}).done(response => {			
			console.log(response.entity.data);
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
				<td><a href="#" onClick={this.handleDetail}>{this.props.character.id} </a></td>
				<td>{this.props.character.name}</td>
				<td>{this.props.character.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)