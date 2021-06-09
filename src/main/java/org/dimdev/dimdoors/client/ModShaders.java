package org.dimdev.dimdoors.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Shader;

@Environment(EnvType.CLIENT)
public class ModShaders {
	private static Shader DIMENSIONAL_PORTAL = null;

	public static void setDimensionalPortal(Shader dimensionalPortal) {
		DIMENSIONAL_PORTAL = dimensionalPortal;
	}

	public static Shader getDimensionalPortal() {
		return DIMENSIONAL_PORTAL;
	}

	private static Shader STATIC = null;

	public static void setStaticShader(Shader dimensionalPortal) {
		STATIC = dimensionalPortal;
	}

	public static Shader getStaticShader() {
		return STATIC;
	}
}
