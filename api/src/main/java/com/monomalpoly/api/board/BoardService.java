package com.monomalpoly.api.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.property.Property;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.chance.ChanceRepository;

@Service
public class BoardService {
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private ChanceRepository chanceRepository;

	@Autowired
	private BoardRepository boardRepository;

	public Board createBoard(Game game) {
		Board b = new Board();

		Property p = new Property();
		p.setName("Go");
		p.setNature("depart");
		p.setBuyable(false);
		p.setUser(null);
		p.setLandCost(0);
		p.setCost(0);
		p.setLevel(0);
		p.setColor(null);
		propertyRepository.save(p);
		b.getCards().add(p);

		Property p2 = new Property();
		p2.setName("MEDITERRANEAN AVENUE");
		p2.setNature("normal");
		p2.setBuyable(true);
		p2.setUser(null);
		p2.setLandCost(50);
		p2.setCost(50);
		p2.setLevel(0);
		p2.setColor("purple");
		propertyRepository.save(p2);
		b.getCards().add(p2);

		Chance p3 = new Chance();
		chanceRepository.save(p3);
		b.getCards().add(p3);

		Property p4 = new Property();
		p4.setName("BALTIC AVENUE");
		p4.setNature("normal");
		p4.setBuyable(true);
		p4.setUser(null);
		p4.setLandCost(50);
		p4.setCost(50);
		p4.setLevel(0);
		p4.setColor("purple");
		propertyRepository.save(p4);
		b.getCards().add(p4);

		Chance p5 = new Chance();
		chanceRepository.save(p5);
		b.getCards().add(p5);

		Property p6 = new Property();
		p6.setName("READING RAILROAD");
		p6.setNature("public");
		p6.setBuyable(true);
		p6.setUser(null);
		p6.setLandCost(200);
		p6.setCost(200);
		p6.setLevel(0);
		p6.setColor(null);
		propertyRepository.save(p6);
		b.getCards().add(p6);

		Property p7 = new Property();
		p7.setName("ORIENTAL AVENUE");
		p7.setNature("normal");
		p7.setBuyable(true);
		p7.setUser(null);
		p7.setLandCost(100);
		p7.setCost(100);
		p7.setLevel(0);
		p7.setColor("grey");
		propertyRepository.save(p7);
		b.getCards().add(p7);

		Chance p8 = new Chance();
		chanceRepository.save(p8);
		b.getCards().add(p8);

		Property p9 = new Property();
		p9.setName("VERMONT AVENUE");
		p9.setNature("normal");
		p9.setBuyable(true);
		p9.setUser(null);
		p9.setLandCost(100);
		p9.setCost(100);
		p9.setLevel(0);
		p9.setColor("grey");
		propertyRepository.save(p9);
		b.getCards().add(p9);

		Property p10 = new Property();
		p10.setName("CONNECTICUT AVENUE");
		p10.setNature("normal");
		p10.setBuyable(true);
		p10.setUser(null);
		p10.setLandCost(120);
		p10.setCost(120);
		p10.setLevel(0);
		p10.setColor("grey");
		propertyRepository.save(p10);
		b.getCards().add(p10);

		Property p11 = new Property();
		p11.setName("PRISON");
		p11.setNature("jail");
		p11.setBuyable(false);
		p11.setUser(null);
		p11.setLandCost(0);
		p11.setCost(0);
		p11.setLevel(0);
		p11.setColor(null);
		propertyRepository.save(p11);
		b.getCards().add( p11);

		Property p12 = new Property();
		p12.setName("ST. CHARLES PLACE");
		p12.setNature("normal");
		p12.setBuyable(true);
		p12.setUser(null);
		p12.setLandCost(140);
		p12.setCost(140);
		p12.setLevel(0);
		p12.setColor("pink");
		propertyRepository.save(p12);
		b.getCards().add( p12);

		Property p13 = new Property();
		p13.setName("ELECTRIC COMPANY");
		p13.setNature("public");
		p13.setBuyable(true);
		p13.setUser(null);
		p13.setLandCost(150);
		p13.setCost(150);
		p13.setLevel(0);
		p13.setColor(null);
		propertyRepository.save(p13);
		b.getCards().add( p13);

		Property p14 = new Property();
		p14.setName("STATES AVENUE");
		p14.setNature("normal");
		p14.setBuyable(true);
		p14.setUser(null);
		p14.setLandCost(140);
		p14.setCost(140);
		p14.setLevel(0);
		p14.setColor("pink");
		propertyRepository.save(p14);
		b.getCards().add( p14);

		Property p15 = new Property();
		p15.setName("VIRGINIA AVENUE");
		p15.setNature("normal");
		p15.setBuyable(true);
		p15.setUser(null);
		p15.setLandCost(160);
		p15.setCost(160);
		p15.setLevel(0);
		p15.setColor("pink");
		propertyRepository.save(p15);
		b.getCards().add( p15);

		Property p16 = new Property();
		p16.setName("PENNSYLVANIA RAILROAD");
		p16.setNature("public");
		p16.setBuyable(true);
		p16.setUser(null);
		p16.setLandCost(200);
		p16.setCost(200);
		p16.setLevel(0);
		p16.setColor(null);
		propertyRepository.save(p16);
		b.getCards().add( p16);

		Property p17 = new Property();
		p17.setName("ST. JAMES AVENUE");
		p17.setNature("normal");
		p17.setBuyable(true);
		p17.setUser(null);
		p17.setLandCost(180);
		p17.setCost(180);
		p17.setLevel(0);
		p17.setColor("orange");
		propertyRepository.save(p17);
		b.getCards().add( p17);

		Chance p18 = new Chance();
		chanceRepository.save(p18);
		b.getCards().add( p18);

		Property p19 = new Property();
		p19.setName("TENNESSEE AVENUE");
		p19.setNature("normal");
		p19.setBuyable(true);
		p19.setUser(null);
		p19.setLandCost(180);
		p19.setCost(180);
		p19.setLevel(0);
		p19.setColor("orange");
		propertyRepository.save(p19);
		b.getCards().add( p19);

		Property p20 = new Property();
		p20.setName("NEW YORK AVENUE");
		p20.setNature("normal");
		p20.setBuyable(true);
		p20.setUser(null);
		p20.setLandCost(200);
		p20.setCost(200);
		p20.setLevel(0);
		p20.setColor("orange");
		propertyRepository.save(p20);
		b.getCards().add( p20);

		Property p21 = new Property();
		p21.setName("PARKING GRATUIT");
		p21.setNature("parking");
		p21.setBuyable(false);
		p21.setUser(null);
		p21.setLandCost(0);
		p21.setCost(0);
		p21.setLevel(0);
		p21.setColor(null);
		propertyRepository.save(p21);
		b.getCards().add( p21);

		Property p22 = new Property();
		p22.setName("KENTUCKY AVENUE");
		p22.setNature("normal");
		p22.setBuyable(true);
		p22.setUser(null);
		p22.setLandCost(220);
		p22.setCost(220);
		p22.setLevel(0);
		p22.setColor("red");
		propertyRepository.save(p22);
		b.getCards().add( p22);

		Chance p23 = new Chance();
		chanceRepository.save(p23);
		b.getCards().add( p23);

		Property p24 = new Property();
		p24.setName("INDIANA AVENUE");
		p24.setNature("normal");
		p24.setBuyable(true);
		p24.setUser(null);
		p24.setLandCost(220);
		p24.setCost(220);
		p24.setLevel(0);
		p24.setColor("red");
		propertyRepository.save(p24);
		b.getCards().add( p24);

		Property p25 = new Property();
		p25.setName("ILLINOIS AVENUE");
		p25.setNature("normal");
		p25.setBuyable(true);
		p25.setUser(null);
		p25.setLandCost(200);
		p25.setCost(200);
		p25.setLevel(0);
		p25.setColor("red");
		propertyRepository.save(p25);
		b.getCards().add( p25);

		Property p26 = new Property();
		p26.setName("B&O RAILROAD");
		p26.setNature("public");
		p26.setBuyable(true);
		p26.setUser(null);
		p26.setLandCost(200);
		p26.setCost(200);
		p26.setLevel(0);
		p26.setColor(null);
		propertyRepository.save(p26);
		b.getCards().add( p26);

		Property p27 = new Property();
		p27.setName("ATLANTIC AVENUE");
		p27.setNature("normal");
		p27.setBuyable(true);
		p27.setUser(null);
		p27.setLandCost(260);
		p27.setCost(260);
		p27.setLevel(0);
		p27.setColor("yellow");
		propertyRepository.save(p27);
		b.getCards().add( p27);

		Property p28 = new Property();
		p28.setName("VENTNOR AVENUE");
		p28.setNature("normal");
		p28.setBuyable(true);
		p28.setUser(null);
		p28.setLandCost(260);
		p28.setCost(260);
		p28.setLevel(0);
		p28.setColor("yellow");
		propertyRepository.save(p28);
		b.getCards().add( p28);

		Property p29 = new Property();
		p29.setName("WATERWORKS");
		p29.setNature("public");
		p29.setBuyable(true);
		p29.setUser(null);
		p29.setLandCost(120);
		p29.setCost(120);
		p29.setLevel(0);
		p29.setColor(null);
		propertyRepository.save(p29);
		b.getCards().add( p29);

		Property p30 = new Property();
		p30.setName("MARVIN GARDENS");
		p30.setNature("normal");
		p30.setBuyable(true);
		p30.setUser(null);
		p30.setLandCost(280);
		p30.setCost(280);
		p30.setLevel(0);
		p30.setColor("yellow");
		propertyRepository.save(p30);
		b.getCards().add( p30);

		Property p31 = new Property();
		p31.setName("GO TO JAIL");
		p31.setNature("gojail");
		p31.setBuyable(false);
		p31.setUser(null);
		p31.setLandCost(0);
		p31.setCost(0);
		p31.setLevel(0);
		p31.setColor(null);
		propertyRepository.save(p31);
		b.getCards().add( p31);

		Property p32 = new Property();
		p32.setName("PACIFIC AVENUE");
		p32.setNature("normal");
		p32.setBuyable(true);
		p32.setUser(null);
		p32.setLandCost(300);
		p32.setCost(300);
		p32.setLevel(0);
		p32.setColor("green");
		propertyRepository.save(p32);
		b.getCards().add( p32);

		Property p33 = new Property();
		p33.setName("NORTH CAROLINA AVENUE");
		p33.setNature("normal");
		p33.setBuyable(true);
		p33.setUser(null);
		p33.setLandCost(300);
		p33.setCost(300);
		p33.setLevel(0);
		p33.setColor("green");
		propertyRepository.save(p33);
		b.getCards().add( p33);

		Chance p34 = new Chance();
		chanceRepository.save(p34);
		b.getCards().add( p34);

		Property p35 = new Property();
		p35.setName("PENNSYLVANIA AVENUE");
		p35.setNature("normal");
		p35.setBuyable(true);
		p35.setUser(null);
		p35.setLandCost(320);
		p35.setCost(320);
		p35.setLevel(0);
		p35.setColor("green");
		propertyRepository.save(p35);
		b.getCards().add( p35);

		Property p36 = new Property();
		p36.setName("SHORT LINE");
		p36.setNature("public");
		p36.setBuyable(true);
		p36.setUser(null);
		p36.setLandCost(200);
		p36.setCost(200);
		p36.setLevel(0);
		p36.setColor(null);
		propertyRepository.save(p36);
		b.getCards().add( p36);

		Chance p37 = new Chance();
		chanceRepository.save(p37);
		b.getCards().add( p37);

		Property p38 = new Property();
		p38.setName("PARK PLACE");
		p38.setNature("normal");
		p38.setBuyable(true);
		p38.setUser(null);
		p38.setLandCost(350);
		p38.setCost(350);
		p38.setLevel(0);
		p38.setColor("blue");
		propertyRepository.save(p38);
		b.getCards().add( p38);

		Chance p39 = new Chance();
		chanceRepository.save(p39);
		b.getCards().add( p39);

		Property p40 = new Property();
		p40.setName("BOARDWALK");
		p40.setNature("normal");
		p40.setBuyable(true);
		p40.setUser(null);
		p40.setLandCost(400);
		p40.setCost(400);
		p40.setLevel(0);
		p40.setColor("blue");
		propertyRepository.save(p40);
		b.getCards().add( p40);

		boardRepository.save(b);

		game.setBoard(b);

		return b;
	}
}
